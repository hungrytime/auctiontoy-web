package com.auctiontoyweb.client


//import com.auctiontoyweb.config.FeignConfig
import com.auctiontoyweb.DTO.*
import com.auctiontoyweb.DTO.member.MemberInfoDTO
import com.auctiontoyweb.DTO.member.MemberTokenDTO
import com.auctiontoyweb.DTO.member.SignInMemberDTO
import com.auctiontoyweb.DTO.member.SignUpMemberDTO
//import com.auctiontoyweb.config.ClientErrorDecoder
import com.auctiontoyweb.config.FeignConfig
//import com.auctiontoyweb.config.FeignErrorDecoder
import com.auctiontoyweb.config.FeignHeaderConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name="member", url="127.0.0.1:8080", configuration = [FeignConfig::class, FeignHeaderConfig::class])
interface MemberClient {

    @GetMapping(
        "/member/info",
        produces = ["application/json"]
    )
    fun getInfo(@RequestParam("memberId") memberId: String): ResponseDTO<MemberInfoDTO>

    @PostMapping(
        "/member/sign-in",
        produces = ["application/json"]
    )
    fun login(@RequestBody member: SignInMemberDTO): ResponseDTO<MemberTokenDTO>

    @PostMapping(
        "/member/sign-up",
        produces = ["application/json"]
    )
    fun signUp(@RequestBody member: SignUpMemberDTO): ResponseDTO<String>

    @PostMapping(
        "/member/logout" ,
        produces = ["application/json"]
    )
    fun logout(): ResponseDTO<String>

    @PostMapping(
        "/hello/buddy",
        produces = ["application/json"]
    )
    fun signIn(@RequestBody memberId: SignInMemberDTO): ResponseDTO<String>

    @PostMapping(
        "/member/revoke",
        produces = ["application/json"]
    )
    fun revoke(@RequestParam memberUserId: String): ResponseDTO<String>

    @GetMapping(
        "/hello",
        produces = ["application/json"]
    )
    fun hello(@RequestParam("memberId") memberId: String): ResponseDTO<String>











}