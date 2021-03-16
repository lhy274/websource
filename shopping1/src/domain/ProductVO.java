package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductVO {
	private int goods_num;
	private String goods_category;
	private String goods_name;
	private String goods_content;
	private String goods_size;
	private String goods_color;
	private int goods_amount;
	private int goods_price;
	private String goods_date;

	
}


