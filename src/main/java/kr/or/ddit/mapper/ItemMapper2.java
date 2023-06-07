package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.Item2;

public interface ItemMapper2 {

	List<Item2> list();


	void register(Item2 item);


	Item2 read(int itemId);


	String getPicture2(int itemId);


	String getPicture(int itemId);


	void modify(Item2 item);


	void remove(int itemId);

}
