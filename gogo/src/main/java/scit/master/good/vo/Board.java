package scit.master.good.vo;

import lombok.Data;

@Data
public class Board {
	private int boardnum;
	private String userid;
	private String title;
	private String text;
	private int    hitcount;
	private String regdate;
	private String originalfile;
	private String savedfile;
}
