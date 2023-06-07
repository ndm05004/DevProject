package kr.or.ddit.controller.file.item02.service;

import java.util.List;

import kr.or.ddit.vo.Item2;

public interface ItemService2 {

	List<Item2> list();

	void register(Item2 item);

	Item2 read(int itemId);

	String getPicture2(int itemId);

	String getPicture(int itemId);

	void modify(Item2 item);

	void remove(int itemId);

}
