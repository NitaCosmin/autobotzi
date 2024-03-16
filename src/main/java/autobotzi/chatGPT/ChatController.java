//package autobotzi.chatGPT;
//
//
//import org.springframework.ai.client.AiClient;
//import org.springframework.ai.prompt.Prompt;
//import org.springframework.ai.prompt.messages.ChatMessage;
//import org.springframework.ai.prompt.messages.Message;
//import org.springframework.ai.prompt.messages.MessageType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class ChatController {
//
//    @Autowired
//    private AiClient aiClient;
//
//
//    private static final String ROLE_INFO_KEY = "role";
//
//
//    @GetMapping("/chat")
//    public String chat(@RequestParam String prompt) {
//        // create a request
//        System.out.println(String.format("Sending chat prompts to AI service. One moment please...\r\n"));
//
//        final List<Message> msgs = new ArrayList<>();
//
//        msgs.add(new ChatMessage(MessageType.SYSTEM, prompt));
//
//        final var resps = aiClient.generate(new Prompt(msgs));
//
//        System.out.println(String.format("Prompt created %d generated response(s).", resps.getGenerations().size()));
//
//        resps.getGenerations().stream()
//                .forEach(gen -> {
//                    final var role = gen.getInfo().getOrDefault(ROLE_INFO_KEY, MessageType.ASSISTANT.getValue());
//
//                    System.out.println(String.format("Generated respose from \"%s\": %s", role, gen.getText()));
//                });
//
//        return resps.getGenerations().get(0).getText();
//    }
//}