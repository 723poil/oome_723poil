package org.oome.core.api.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RegExUtils;
import org.oome.core.api.exception.runtime.FileOomeRuntimeException;
import org.oome.core.utils.S;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

@Component
public class CommonFIleUtils {

    /**
     * 배포환경이 linux 이기 때문에 역슬래쉬(\)를 슬래쉬(/)로 변환
     * @param filePath 경로
     * @return linux로 치환된 경로
     */
    private String replaceFileSeperator(String filePath) {
        return RegExUtils.replaceAll(filePath, "\\\\", "/");
    }

    public String transferTo(MultipartFile mpf, String physicalPathParam){

        String physicalPath = replaceFileSeperator(physicalPathParam);

        File path = new File(physicalPath);
        if(!path.exists()) {
            if(!path.mkdirs()) {
                throw new FileOomeRuntimeException(MessageFormat.format("디렉토리({0}) 생성 중에 오류가 발생했습니다.", physicalPath));
            }
        }


        File file = new File(path, Objects.requireNonNull(mpf.getOriginalFilename()));

        int num = 1;
        while(file.exists()) {

            if(num > 100) throw new FileOomeRuntimeException("중복파일 체크(100회 초과) 오류가 발생했습니다.");

            file = new File(path,
                    S.f("{0}[{1}].{2}",
                            FilenameUtils.removeExtension(mpf.getOriginalFilename()).replaceAll("\\[[0-9]+\\]", ""),
                            num++,
                            FilenameUtils.getExtension(mpf.getOriginalFilename())
                    )
            );
        }

        try {
            mpf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            throw new FileOomeRuntimeException("첨부파일 생성 중에 오류가 발생했습니다.");
        }

        File savedFile = new File(path, Objects.requireNonNull(file.getName()));

        if (!savedFile.exists()) {
            throw new FileOomeRuntimeException("저장된 파일이 없습니다.");
        }

        return file.getName();
    }
}
