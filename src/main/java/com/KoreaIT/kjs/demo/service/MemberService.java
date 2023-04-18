package com.KoreaIT.kjs.demo.service;

import org.springframework.stereotype.Service;

import com.KoreaIT.kjs.demo.repository.MemberRepository;
import com.KoreaIT.kjs.demo.util.Ut;
import com.KoreaIT.kjs.demo.vo.Member;
import com.KoreaIT.kjs.demo.vo.ResultData;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		// 로그인 아이디 중복체크
		Member existsMember = getMemberByLoginId(loginId);
		
		if (existsMember != null) {
			return ResultData.from("F-7", Ut.f("이미 사용 중인 아이디(%s)입니다.", loginId));
		}
		
		// 이름 + 이메일 중복체크
		existsMember = getMemberByNameAndEmail(name, email);
		
		if (existsMember != null) {
			return ResultData.from("F-8", Ut.f("동일한 이름(%s)과 이메일(%s)이 사용 중입니다.", name, email));
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
//	public int getCountOfLoginId(String loginId) {
//		return memberRepository.getCountOfLoginId(loginId);
//	}
}
