package com.auctiontoyweb.client

import com.auctiontoyweb.MemberInfoDTO
import com.auctiontoyweb.SignInMemberDTO
import com.auctiontoyweb.SignUpMemberDTO
import com.auctiontoyweb.exception.BusinessException
import com.auctiontoyweb.exception.LogInErrorException
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberClient: MemberClient
) {

//    fun  getMemberInfo(memberId: Long): String {
//        val ret = memberClient.getInfo(memberId.toString()).content
//        return ret?.memberName ?: "NONE"
//    }
//
    fun getMemberInfo(memberId: String) : MemberInfoDTO? {
        var ret: MemberInfoDTO? = null
        try{
            ret = memberClient.getInfo(memberId).content
        } catch (e: LogInErrorException) {
            return null
        } catch (e: Exception) {
            println(e)
        }
        return ret
    }

    fun login(memberInfo: SignInMemberDTO): String {
        println("${UserInfoValue.token}")
        val ret = memberClient.login(memberInfo)
        if(!ret.success) {
            throw BusinessException(ret.message)
        }

//        ret = ret?.substring(0, ret.length-1)
        UserInfoValue.token = ret.content?.token ?: "NONE"
        if (ret.content != null) {
            UserInfoValue.userId = memberInfo.id
            UserInfoValue.userMemberId = ret.content?.memberId!!
        }

        return ret.content?.token ?: "NONE"
    }

    fun signUp(memberInfo: SignUpMemberDTO): String {
        println("${UserInfoValue.token}")
        var ret = memberClient.signUp(memberInfo).content
        if (ret != null && ret.equals("OK")) UserInfoValue.userId = memberInfo.id

        return ret ?: "NONE"
    }

    fun logout(): Boolean {
        val ret = memberClient.logout().content
        if (ret == "OK") return true
        return false
    }


    fun signIn(memberInfo: SignInMemberDTO): String {
        val ret = memberClient.signIn(memberInfo).content
        return ret ?: "NONE"
    }

    fun revoke(): String? {
        val ret = memberClient.revoke(UserInfoValue.userId).content
        return ret
    }

    fun hello(memberId: Long): String {
        val ret = memberClient.hello(memberId.toString()).content
        return ret ?: "NONE"
    }
}