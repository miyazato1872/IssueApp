package in.techcamp.issueapp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class IssueController {

    private final IssueRepository issueRepository;

    @GetMapping("/issueForm")
    public String showIssueForm(@ModelAttribute("issueForm") IssueForm form){  //IssueForm型の変数formを登録し、ビューで「issueForm」という名称で呼び出すことができる
        return "issueForm";
    }

    @PostMapping("/issues")
    public String createIssue(IssueForm issueForm){
        issueRepository.insert(issueForm.getTitle(), issueForm.getContent(), issueForm.getPeriod(), issueForm.getImportance());
        return "redirect:/";
    }

    @GetMapping
    public String showIssues(Model model){
        var issueList = issueRepository.findAll();  //『var』を使うと値の種類によってデータ型が推論され、推論されたデータ型で宣言が行われ「型推論」
        model.addAttribute("issueList", issueList);
        return "index";
    }
}