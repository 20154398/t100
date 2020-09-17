package com.ty.t100.controller;


import com.ty.t100.entity.Task;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.service.FileService;
import com.ty.t100.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
@Api(value = "API - FileController", description = "文件")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @PutMapping("/upload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "query", dataType = "file")
    })
    public String list(String userId, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file == null) throw new BusinessException("上传的空文件");
        System.getProperty("user.dir");
        String fileName = file.getOriginalFilename();
        Path dir = Paths.get(System.getProperty("user.dir"), "file", userId);
        try {
            if (!Files.isDirectory(dir)) {
                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            throw new BusinessException("创建用户文件夹失败");
        }
        File dest = new File(dir + File.separator + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
        } catch (IOException e) {
            throw new BusinessException("文件转换失败");
        }
        return Utils.getFileUrl(request, userId, fileName);
    }
}
