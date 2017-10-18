package org.hui.smart.framework.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hui.smart.framework.bean.FileParam;
import org.hui.smart.framework.bean.FormParam;
import org.hui.smart.framework.bean.Param;
import org.hui.smart.framework.util.CollectionUtil;
import org.hui.smart.framework.util.FileUtil;
import org.hui.smart.framework.util.StreamUtil;
import org.hui.smart.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2017/10/17.
 */
public final class UploadHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    private static ServletFileUpload servletFileUpload;

    /**
     * 初始化
     * @param servletContext
     */
    public static void init(ServletContext servletContext) {
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(
                DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository
        ));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if (uploadLimit != 0) {
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * 表单是否为multipart类型
     * 只有上传文件时才是multipart类型
     * @param request
     * @return
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * 创建请求对象Param
     * @param request
     * @return
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = new ArrayList<>();
        List<FileParam> fileParamList = new ArrayList<>();

        try {
            Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
            if (CollectionUtil.isNotEmpty(fileItemListMap)) {
                for (Map.Entry<String, List<FileItem>> fileItemListEntry: fileItemListMap.entrySet()) {
                    String fieldName = fileItemListEntry.getKey();
                    List<FileItem> fileItemList = fileItemListEntry.getValue();
                    if (CollectionUtil.isNotEmpty(fileItemList)) {
                        for (FileItem fileItem : fileItemList) {
                            if (fileItem.isFormField()) {
                                // 普通表单字段
                                String fieldValue = fileItem.getString("UTF-8");
                                formParamList.add(new FormParam(fieldName, fieldValue));
                            } else {
                                // 文件上传字段
                                String fileName = FileUtil.getRealFileName(
                                        new String(fileItem.getName().getBytes(), "UTF-8")
                                );
                                if (StringUtil.isNotEmpty(fileName)) {
                                    long fileSize = fileItem.getSize();
                                    String contentType = fileItem.getContentType();
                                    InputStream inputStream = fileItem.getInputStream();
                                    fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException | UnsupportedEncodingException e) {
            LOGGER.error("create param failure", e);
            throw new RuntimeException(e);
        }
        return new Param(formParamList, fileParamList);
    }

    /**
     * 上传文件
     * @param basePath
     * @param fileParam
     */
    public static void uploadFile(String basePath, FileParam fileParam) {
        try {
            if (fileParam != null) {
                String filePath = basePath + fileParam.getFileName();
                FileUtil.createFile(filePath);
                InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                StreamUtil.copyStream(inputStream, outputStream);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 多文件上传
     * @param basePath
     * @param fileParamList
     */
    public static void uploadFile(String basePath, List<FileParam> fileParamList) {
        try {
            if (CollectionUtil.isNotEmpty(fileParamList)) {
                for (FileParam fileParam : fileParamList) {
                    uploadFile(basePath, fileParam);
                }
            }
        } catch (Exception e) {
            LOGGER.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }
}
