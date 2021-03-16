package com.example.hapiness.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.hapiness.model.entity.post.HapinessPostCreateEntity;
import com.example.hapiness.model.entity.post.HapinessPostEntity;
import com.example.hapiness.model.entity.post.HapinessPostFindOneEntity;
import com.example.hapiness.model.repository.HapinessPostRepository;

@Service
@Transactional
public class HapinessPostService {

	@Autowired
	private HapinessPostRepository hapinessPostRepository;

	public String getHapinessPostService(Model model) {
		List<HapinessPostEntity> posts = hapinessPostRepository.findAll();
		model.addAttribute("posts", posts);
		System.out.println("コントローラ");
		// templates以下の表示したいファイル名
				return "post/hapinessPostIndex";
				//template以下のファイル名で返すのはSprintDataJPAの仕様？パスで返す方法を調べる→Thymeleafいれて解決
				//Thymeleafの仕様ぽい、調べないとなぁ→
	}

	public String getHapinessPostServiceNew(Model model) {
		HapinessPostCreateEntity hapinessPostCreateEntity = new HapinessPostCreateEntity();
		model.addAttribute("hapinessPostCreateEntity", hapinessPostCreateEntity);
		return "post/hapinessPostNew";
	}

	public String setHapinessPostServiceCreate(@Validated HapinessPostCreateEntity hapinessPostCreateEntity, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/post/hapinessPostNew";
		}

		HapinessPostEntity hapinessPostEntity = new HapinessPostEntity();
		hapinessPostEntity.setBody(hapinessPostCreateEntity.getBody());
		//ログイン機能追加したらuserIdのロジックは変更する（とりあえずuserid1流しとく)

		hapinessPostRepository.save(hapinessPostCreateEntity);
		return "redirect:/post";//とりあえずindexに遷移
		//詳細画面に遷移したい場合、repositoryクラスのsaveメソッドをhibernateの保存方法に変更する。
		//格納されている「query」変数をlist型に変換してreturnで返す。contorllerでlist変数に格納
		//List list = hapinessPostRepository.save(hapinessPostCreateEntity); みたいな感じで
		//return "redirect:/post/hapinessPostShow/list.userId/list.createDateTime";みたいになると思う
	}

	public String getHapinessPostServiceShow(Integer userId, String createDateTime, Model model) {
		//HapinessPostEntity hapinessPostEntity = hapinessPostRepository.findByuserIdAndCreateDateTime(userId, createDateTime);
		HapinessPostFindOneEntity hapinessPostFindOneEntity = hapinessPostRepository.findByuserIdAndCreateDateTime(userId, createDateTime);
		model.addAttribute("hapinessPostFindOneEntity", hapinessPostFindOneEntity);
		return "/post/hapinessPostShow";
	}

	public String getHapinessPostServiceEdit(Integer userId, String createDateTime, Model model) {
		HapinessPostFindOneEntity hapinessPostFindOneEntity = hapinessPostRepository.edit(userId, createDateTime);
		model.addAttribute("hapinessPostFindOneEntity", hapinessPostFindOneEntity);
		return "/post/hapinessPostEdit";
	}

	public String setHapinessPostServiceUpdate(Integer userId, String createDateTime, HapinessPostEntity hapinessPostEntity) {

		hapinessPostRepository.update(userId, createDateTime, hapinessPostEntity);
		System.out.println("updateしたよ");
		return "redirect:/post";
	}

	public String getHapinessPostServiceDelete(Integer userId, String createDateTime) {

		hapinessPostRepository.delete(userId, createDateTime);
		System.out.println("deleteしたよ");
		return "redirect:/post";
	}

}
