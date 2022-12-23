package com.bigdata6.spring_mybatis;

import com.bigdata6.spring_mybatis.dto.BoardImgDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImgFileUploadUtil {
    public static String save(MultipartFile imgFile,String imgPath, String name) throws IOException {
        String fileName=null;
        if(imgFile!=null && !imgFile.isEmpty()){
            String []contentsTypes=imgFile.getContentType().split("/");
            if (contentsTypes[0].equals("image")){
                    fileName="board_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentsTypes[1];
                    Path path = Paths.get(imgPath+"/"+fileName);
                    imgFile.transferTo(path);
            }
        }
        return fileName;
    }
    public static int remove(String imgPath, String fileName){
        int del=0;
        if(fileName==null) return del;
        File originImgFile=new File(imgPath+"/"+fileName);
        del+=(originImgFile.delete())?1:0;
        return del;
    }
    public static int remove(String imgPath, List<String> fileNames){
        int del=0;
        if(fileNames!=null) return  del;
        for (String fileName : fileNames){
            File originImgFile=new File(imgPath+"/"+fileName);
            del+=(originImgFile.delete())?1:0;

        }

        return del;
    }

}
