package kr.or.ddit.controller.file.item01.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Item;

public interface ItemService {

	void register(Item item);

	List<Item> list();

	Item read(int itemId);


	void modify(Item item);

	String getPicture(int itemId);

	void remove(int itemId);

}
