package com.ty.t100.controller;


import com.ty.t100.entity.CommodityImage;
import com.ty.t100.entity.Task;
import com.ty.t100.exception.BusinessException;
import com.ty.t100.service.CommodityImageService;
import com.ty.t100.service.CommodityService;
import com.ty.t100.service.FileService;
import com.ty.t100.util.FileUtil;
import com.ty.t100.util.UUIDUtils;
import com.ty.t100.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
@Api(value = "API - FileController", description = "文件")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @Resource
    private CommodityImageService commodityImageService;

    @PostMapping("/upload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "query", dataType = "file")
    })
    public String upload(String userId, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file == null || file.getOriginalFilename() == null) throw new BusinessException("上传的空文件");

        String fileName = FileUtil.getInstance().transformName(file.getOriginalFilename());

        Path dir = Paths.get(System.getProperty("user.dir"), "file", userId);
        return saveFile(userId, file, request, fileName, dir);
    }

    @PostMapping("/commodity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "query", dataType = "file")
    })
    public String uploadCommodityImage(String userId, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file == null || file.getOriginalFilename() == null) throw new BusinessException("上传的空文件");

        String fileName = FileUtil.getInstance().transformName(file.getOriginalFilename());

        Path dir = Paths.get(System.getProperty("user.dir"), "commodity-image", userId);
        CommodityImage commodityImage = new CommodityImage().setImageUrl(saveFile(userId, file, request, fileName, dir));
        commodityImageService.saveOrUpdate(commodityImage);
        return commodityImage.getId();
    }

    private String saveFile(String userId, MultipartFile file, HttpServletRequest request, String fileName, Path dir) {
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
        return Utils.getInstance().getFileUrl(request, userId, fileName);
    }
}
