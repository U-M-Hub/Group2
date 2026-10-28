package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

@Service
public class AdminAddBookImageService {
	
	//表紙画像の一覧を取得する
	public List<String> findAllImages() throws IOException{
		//クラスパス上のリソースを検索するための準備
		ResourcePatternResolver resolver =
				new PathMatchingResourcePatternResolver();
		
		//static/images内のファイルを取得する
		Resource[] resources = resolver.getResources("classpath*:/static/images/*");
		
		return Arrays.stream(resources)
				//ファイル名を取り出す
				.map(Resource::getFilename)
				//ファイル名がNULLのものを除外する
				.filter(name -> name != null)
				//Noimage.pngは選択肢から除外する
				.filter(name -> !name.equalsIgnoreCase("Noimage.png"))
				//対応する画像形式だけに絞り込む
				.filter(name -> name.matches("(?i).+\\.(png|jpg|jpeg|gif|webp)"))
				//ファイル名順に並べる
				.sorted()
				//Listに変換して返す
				.toList();
		
	}
	

}
