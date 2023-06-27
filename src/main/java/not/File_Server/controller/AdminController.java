package not.File_Server.controller;

import lombok.AllArgsConstructor;
import not.File_Server.config.JwtService;
import not.File_Server.dto.request.AdminLogInDto;
import not.File_Server.dto.request.AdminSignUpDto;
import not.File_Server.dto.request.FileStatsDto;
import not.File_Server.dto.response.AdminDto;
import not.File_Server.interfaces.AdminService;
import not.File_Server.model.Document;
import not.File_Server.repositories.DocumentRepository;
import not.File_Server.repositories.EmailLogRepository;
import not.File_Server.utils.AdminConvertor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/admin")
@AllArgsConstructor
@RestController
public class AdminController {
    private final AdminService adminService;
    private final AdminConvertor adminConvertor;
    private final JwtService jwtService;

    private final DocumentRepository fileRepository;
    private EmailLogRepository emailLogRepository;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AdminLogInDto request) {
        return ResponseEntity.ok(adminService.authenticateAdmin(request));
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody AdminSignUpDto request) {
        return ResponseEntity.ok(adminService.signUpAdmin(request));
    }

    @GetMapping("/get")
    public ResponseEntity<AdminDto> getAdmin(@RequestHeader("Authorization") String authorizationHeader ) {
        String token = authorizationHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok(adminConvertor.convert(adminService.getAdmin(email)));
    }

//    @GetMapping("/file-stats")
//    public ResponseEntity<List<FileStatsDto>> getFileStats() {
//        List<Document> files = fileRepository.findAll();
//        List<FileStatsDto> fileStats = new ArrayList<>();
//
//        for (Document file : files) {
//            int numDownloads = file.getDownloads().size();
//            int numEmailsSent = emailLogRepository.countByEmailId(file.getId());
//
//            FileStatsDto fileStat = new FileStatsDto(file.getId(), file.getFileName(), numDownloads, numEmailsSent);
//            fileStats.add(fileStat);
//        }
//
//        return ResponseEntity.ok(fileStats);
//    }

}
