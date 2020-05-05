package com.mty.cisp.service;

import com.mty.cisp.model.vo.ResponseJson;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    ResponseJson upload(MultipartFile file, HttpServletRequest request);
}
