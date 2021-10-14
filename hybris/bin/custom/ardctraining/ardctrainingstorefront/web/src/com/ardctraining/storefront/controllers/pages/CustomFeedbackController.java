package com.ardctraining.storefront.controllers.pages;


import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.facades.feedback.CustomerFeedbackFacade;
import com.ardctraining.storefront.forms.MessageFeedbackForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/feedback")
public class CustomFeedbackController extends AbstractPageController {
    @Resource(name = "customerFeedbackFacade")
    private CustomerFeedbackFacade customerFeedbackFacade;
    @Resource(name = "customerFeedbackService")
    private CustomerFeedbackService customerFeedbackService;


    @GetMapping
    public String productDetail(final Model model){
        MessageFeedbackForm messageFeedbackForm = new MessageFeedbackForm();
        model.addAttribute("messageForm",messageFeedbackForm);
        model.addAttribute("data",customerFeedbackFacade.getUnread());
        return "/pages/feedback/feedbackPage";
    }

    @PostMapping("/save")
    public String saveFeedback(MessageFeedbackForm messageForm){
        customerFeedbackFacade.save(messageForm.getSubject(),messageForm.getMessage());
        return "redirect:/feedback";
    }




}
