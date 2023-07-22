package com.auctiontoyweb.controller

import com.auctiontoyweb.DTO.member.SignInMemberDTO
import com.auctiontoyweb.DTO.member.SignUpMemberDTO
import com.auctiontoyweb.client.MemberService
import com.auctiontoyweb.client.UserInfoValue
import com.auctiontoyweb.controller.form.LogInForm
import com.auctiontoyweb.controller.form.SignUpForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(
    val memberService: MemberService
) {

    @GetMapping(value = ["/login", "/"])
    fun login(@ModelAttribute logInForm: LogInForm, model: Model): String {
        if(UserInfoValue.token.isNullOrBlank())
            return "/login"
        val ret = memberService.logout()
        UserInfoValue.token = ""
        return "login"
    }

    @PostMapping("/login")
    fun signIn(@ModelAttribute logInForm: LogInForm, model: Model): String {
        if(logInForm.userId == null || logInForm.password == null) {
            return "login"
        }
        println(logInForm.userId)
        println(logInForm.password)
        val content = memberService.login(SignInMemberDTO.from(logInForm))
        val ret = memberService.getMemberInfo(logInForm.userId) ?: return "redirect:login"
        println(content)
        UserInfoValue.userName = ret.memberName
        model.addAttribute("name", ret.memberName)
        model.addAttribute("userId", logInForm.userId)
        return "redirect:main"
    }

    @GetMapping("/main")
    fun getMain(model: Model): String {
        if(UserInfoValue.token.isNullOrBlank()) return "redirect:login"
        val ret = memberService.getMemberInfo(UserInfoValue.userId) ?: return "redirect:login"

        model.addAttribute("name", ret.memberName)
        model.addAttribute("userId", UserInfoValue.userId)
        return "main"
    }


    @GetMapping("/member-info")
    fun getMemberInfo(model: Model): String {
        if(UserInfoValue.token.isNullOrBlank()) return "redirect:login"
        val ret = memberService.getMemberInfo(UserInfoValue.userId) ?: return "redirect:login"
        model.addAttribute("userName", ret.memberName)
        model.addAttribute("createdAt", ret.createdDate)
        model.addAttribute("userId", UserInfoValue.userId)
        return "member/info"
    }

    @GetMapping("/sign-up")
    fun getSignIn(): String {
        return "signup"
    }

    @PostMapping("/sign-up")
    fun putSignIn(@ModelAttribute signUpForm: SignUpForm, model: Model): String {
        if(signUpForm.userId == null || signUpForm.password == null) {
            return "redirect:login"
        }
        val content = memberService.signUp(SignUpMemberDTO.from(signUpForm))
        return "redirect:login"
    }

    @GetMapping("/log-out")
    fun logout(): String {
        val ret = memberService.logout()
        if (ret) UserInfoValue.token = ""
        return "redirect:login"
    }

    @GetMapping("/revoke")
    fun revoke(): String {
        if(UserInfoValue.userId.isNullOrBlank()) {
            println("탈퇴할 정보가 없습니다")
            return "redirect:login"
        }

        val ret = memberService.revoke()
        if (ret.equals("OK")) println("탈퇴 성공")
        UserInfoValue.token = ""
        UserInfoValue.userId = ""
        return "redirect:login"
    }
}