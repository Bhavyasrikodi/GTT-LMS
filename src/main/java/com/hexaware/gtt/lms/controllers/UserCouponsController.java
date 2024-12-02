package com.hexaware.gtt.lms.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.dto.UserCouponDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.dto.UserValidationDto;
import com.hexaware.gtt.lms.entities.UserCoupons;
import com.hexaware.gtt.lms.services.UserCouponService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lms/api/v1/userCoupons/")
@ResponseBody
public class UserCouponsController {
	private UserCouponService userCouponService;
	private ModelMapper modelmapper;
	
	@Autowired
	public UserCouponsController(UserCouponService userCouponService, ModelMapper modelmapper) {
		this.userCouponService = userCouponService;
		this.modelmapper = modelmapper;
	}	
	
	@PostMapping("generateCoupon/")
	public ResponseEntity<?> generateCoupon(@Valid @RequestBody UserPartnerDto userPartnerDto)
	{
		
		try{
			UUID uId = userCouponService.finduid(userPartnerDto);
			UUID tierId=userCouponService.findTierbyUId(uId);
			float couponProbability=userCouponService.findProbablity(tierId);
			UUID couponId=userCouponService.getCouponId(tierId);
			UserCouponDto userCouponDto;
			if(userCouponService.awardCoupon(couponProbability)) {
				CouponGenerationDto couponGenerationDto = new CouponGenerationDto(couponId,uId);
				UserCoupons userCoupons =userCouponService.generateCoupon(couponGenerationDto);
				userCouponDto=modelmapper.map(userCoupons, UserCouponDto.class);
				userCouponDto.setuId(userCoupons.getUsers().getuId());
				System.out.println("User coupon"+ userCouponDto);
			}
			else {
				System.out.println("Better luck");
				userCouponDto = new UserCouponDto();
			}
			return ResponseEntity.ok(userCouponDto);
		}catch(Exception e){
			
			return ResponseEntity.ok("partner Id/User Id invalid");
		}		
	}
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateCoupon(@RequestBody UserValidationDto userValidationDto) {
		boolean isValid = userCouponService.validateCoupon(userValidationDto);
		if (isValid){
			return ResponseEntity.ok("CouponCode"+" "+userValidationDto.getCouponCode()+" "+ "is valid");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("coupon expired or used");

	}

	@PostMapping("/redeem")
	public ResponseEntity<?> redeemCoupon(@RequestBody UserValidationDto userValidationDto) {
		boolean isValid = userCouponService.redeemCoupon(userValidationDto);
		if (isValid){
			return ResponseEntity.ok("CouponCode"+" "+userValidationDto.getCouponCode()+" "+ "is successfully redeemed");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("coupon expired or used");

	}
	}
	

