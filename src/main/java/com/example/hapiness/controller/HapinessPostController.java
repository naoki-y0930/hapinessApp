package com.example.hapiness.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hapiness.model.entity.post.HapinessPostCreateEntity;
import com.example.hapiness.model.entity.post.HapinessPostEntity;
import com.example.hapiness.service.HapinessPostService;

@Controller
@RequestMapping("post")
public class HapinessPostController {

	@Autowired
	private HapinessPostService hapinessPostService;

	//現在日時取得
	LocalDateTime nowDateTime = LocalDateTime.now();
	DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	String nowDayTime = nowDateTime.format( standardFormat );

	@GetMapping("")
	public String getHapinessPost(Model model) {
		return hapinessPostService.getHapinessPostService(model);
	}

	@GetMapping("/new")
	public String getHapinessPostNew(Model model) {
		return hapinessPostService.getHapinessPostServiceNew(model);
	}

	@PostMapping("")
	public String postHapinessPostCreate(@Validated HapinessPostCreateEntity hapinessPostCreateEntity, BindingResult bindingResult) {
		return hapinessPostService.setHapinessPostServiceCreate(hapinessPostCreateEntity, bindingResult);
	}

	@GetMapping("/{userId}/{createDateTime}")
	public String getHapinessPostShow(@PathVariable("userId") Integer userId, @PathVariable("createDateTime") String createDateTime, Model model) {
		return hapinessPostService.getHapinessPostServiceShow(userId, createDateTime, model);
	}

	@GetMapping("/edit/{userId}/{createDateTime}")
	public String getHapinessPostEdit(@PathVariable("userId") Integer userId, @PathVariable("createDateTime") String createDateTime, Model model) {
		return hapinessPostService.getHapinessPostServiceEdit(userId, createDateTime, model);
	}

	@PostMapping("/{userId}/{createDateTime}")
	// メソッドの引数に@ModelAttributeをつけると送信されたリクエストのbodyの情報を取得できる
	public String postHapinessPostUpdate(@PathVariable("userId") Integer userId, @PathVariable("createDateTime") String createDateTime, @ModelAttribute HapinessPostEntity hapinessPostEntity) {
		return hapinessPostService.setHapinessPostServiceUpdate(userId, createDateTime, hapinessPostEntity);
	}

	@DeleteMapping("/delete/{userId}/{createDateTime}")
	public String getHapinessPostDelete(@PathVariable("userId") Integer userId, @PathVariable("createDateTime") String createDateTime) {
		return hapinessPostService.getHapinessPostServiceDelete(userId, createDateTime);
	}

}
