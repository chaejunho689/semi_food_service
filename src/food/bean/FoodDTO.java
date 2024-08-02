package food.bean;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter	
public class FoodDTO {
		private String id; 				//회원가입 아이디 
		private String pwd; 			// 회원가입 비밀번호 
		private String name; 			// 회원가입 이름
		private int code; 				// 회원가입 이름

		
		private int res_code; 			// 식당 코드
		private String res_name; 		// 식당 이름
		private String res_address; 	// 식당 주소
		private String res_pnumber; 	// 식당 전화번호
		private int res_kind; 			// 식당 종류
		
		
		private int food_code; 			// 음식 코드
		private String food_name; 		// 음식 이름
		private int food_price; 		// 음식 가격
		private int food_kind; 		// 음식 종류
		
		private int seq;
		
}
