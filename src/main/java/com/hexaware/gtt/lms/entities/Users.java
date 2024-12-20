package com.hexaware.gtt.lms.entities;
 
import java.time.LocalDateTime;

import java.util.UUID;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
 
@Entity

public class Users {
 
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID uId;
 
    private long userId;
 
    @ManyToOne

    @JoinColumn(name = "partner_id", nullable = false, updatable = false)

    private Partner partner;
 
    @ManyToOne

    @JoinColumn(name = "tier_id", nullable = false, updatable = true)

    private Tiers tiers;
 
    private double totalPoints;
 
    @Column(updatable = false)

    private final LocalDateTime date = LocalDateTime.now();
 
    private LocalDateTime tierSetDate;

    private LocalDateTime expiry;

    private double tierTransactionAmount; //for tier assignment and it will set to 0 for each tier upgradation

    private double totalTransactionAmount; // total amount that customer spent in our application
 
    

    public Users() {

		super();

		// TODO Auto-generated constructor stub

	}
 
    
 
 
	public Users(UUID uId, long userId, Partner partner, Tiers tiers, double totalPoints, LocalDateTime tierSetDate,LocalDateTime expiry,

			double tierTransactionAmount, double totalTransactionAmount) {

		super();

		this.uId = uId;

		this.userId = userId;

		this.partner = partner;

		this.tiers = tiers;

		this.totalPoints = totalPoints;

		this.tierSetDate = tierSetDate;

		this.expiry = expiry;

		this.tierTransactionAmount = tierTransactionAmount;

		this.totalTransactionAmount = totalTransactionAmount;

	}
 
 
 
	public UUID getuId() {

        return uId;

    }
 
    public void setuId(UUID uId) {

        this.uId = uId;

    }
 
    public long getUserId() {

        return userId;

    }
 
    public void setUserId(long userId) {

        this.userId = userId;

    }
 
    public Partner getPartner() {

        return partner;

    }
 
    public void setPartner(Partner partner) {

        this.partner = partner;

    }
 
    public Tiers getTiers() {

        return tiers;

    }
 
    public void setTiers(Tiers tiers) {

        this.tiers = tiers;

    }
 
    
 
    public double getTotalPoints() {

		return totalPoints;

	}
 
	public void setTotalPoints(double totalPoints) {

		this.totalPoints = totalPoints;

	}
 
	public LocalDateTime getDate() {

        return date;

    }
 
    public LocalDateTime getExpiry() {

        return expiry;

    }
 
    public void setExpiry(LocalDateTime expiry) {

        this.expiry = expiry;

    }
 
	@Override

	public String toString() {

		return "Users [uId=" + uId + ", userId=" + userId + ", partner=" + partner + ", tiers=" + tiers

				+ ", totalPoints=" + totalPoints + ", date=" + date + ", expiry=" + expiry + "]";

	}
 
 
	public double getTierTransactionAmount() {

		return tierTransactionAmount;

	}
 
 
	public void setTierTransactionAmount(double tierTransactionAmount) {

		this.tierTransactionAmount = tierTransactionAmount;

	}
 
 
	public double getTotalTransactionAmount() {

		return totalTransactionAmount;

	}
 
 
	public void setTotalTransactionAmount(double totalTransactionAmount) {

		this.totalTransactionAmount = totalTransactionAmount;

	}
 
 
 
	public LocalDateTime getTierSetDate() {

		return tierSetDate;

	}
 
 
 
	public void setTierSetDate(LocalDateTime tierSetDate) {

		this.tierSetDate = tierSetDate;

	}


    
   

}

