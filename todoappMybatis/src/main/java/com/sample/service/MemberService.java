package com.sample.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sample.dao.MemberDao;
import com.sample.dto.Member;
import com.sample.util.Util;

public class MemberService {
	
//	全てのメンバーをリストで取得
//	@ResultMap("memberResult")
//	@Select("select * from member;")
	public List<Member> findAll() {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		MemberDao memberdao = session.getMapper(MemberDao.class);
		
//		MemberDaoインターフェースからリストを取得
		List<Member> memberList = memberdao.findAll();
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return memberList;
		
	}
	
//	Mapでリファクタリング全てのメンバーをリストで取得
//	@Mapkeyでmapにできる
//	@ResultMap("memberResult")
//	@Select("select * from member;")
	public Map<Integer, Member> findAllByMap() {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		MemberDao memberdao = session.getMapper(MemberDao.class);
		
//		MemberDaoインターフェースからリストを取得
		Map<Integer, Member> memberMap = memberdao.findAllByMap();
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return memberMap;
		
	}
	
//	ログインチェック
	public Member findByLoginMember(String name, String password) {
		
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		MemberDao memberdao = session.getMapper(MemberDao.class);
		
//		Memberインスタンスを作る
		Member member = new Member();
		member.setName(name);
		member.setPassword(password);
//		MemberDaoインターフェースからリストを取得
		Member loginMember = null;
		try {
			loginMember = memberdao.findByLoginMember(member);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			
//		セッションをコミット
			session.commit();
//		セッションを閉じる
			session.close();
//		メンバー情報をを返す
		}
		
		return loginMember;

	}
}

