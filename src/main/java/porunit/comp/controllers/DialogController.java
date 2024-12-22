package porunit.comp.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porunit.comp.data.domain.DialogSession;
import porunit.comp.data.dto.DialogDTO;
import porunit.comp.data.dto.MessageDTO;
import porunit.comp.services.DialogService;

import java.util.List;

@RestController
@RequestMapping("/api/dialogs")
public class DialogController {

    private final DialogService dialogService;

    public DialogController(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @PostMapping
    public ResponseEntity<?> createDialog(@RequestBody DialogDTO dialogDTO, @RequestHeader("Authorization") String token) {
        dialogService.saveDialog(dialogDTO, token);
        return ResponseEntity.ok("legit");
    }

    @PostMapping("/{sessionId}/messages")
    public ResponseEntity<?> createMessage(@PathVariable Long sessionId, @RequestBody MessageDTO messageDTO) {
        System.out.println(messageDTO);
        return ResponseEntity.ok(dialogService.saveMessage(sessionId, messageDTO));
    }

    @GetMapping
    public ResponseEntity<List<Integer>> getAllDialogs(@RequestHeader("Authorization") String token) {
        List<Integer> dialogs = dialogService.getAllDialogsForUser(token);
        return ResponseEntity.ok(dialogs);
    }

    @GetMapping("/{sessionId}/messages")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable Long sessionId) {
        return ResponseEntity.ok(dialogService.getAllMessagesForDialog(sessionId));
    }
}
