package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leavebalance")
@Data
@NoArgsConstructor
public class LeaveBalance {
    @Id
    @Column(name = "userleaveid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userleaveid; 
    
    @ManyToOne
    @JoinColumn(name="name", nullable=false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name="type", nullable=false)
    private LeaveType leavetype;
    
    @Column(name = "balance")
    private double balance;

	public LeaveBalance(User user, LeaveType leavetype, double balance) {
		super();
		this.user = user;
		this.leavetype = leavetype;
		this.balance = balance;
	} 
}
