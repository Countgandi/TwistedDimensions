package com.countgandi.com.game;

import java.util.ArrayList;

import com.countgandi.com.game.skills.BowSkill;
import com.countgandi.com.game.skills.EnergySkill;
import com.countgandi.com.game.skills.HealthSkill;
import com.countgandi.com.game.skills.Skill;
import com.countgandi.com.game.skills.SwordSkill;
import com.countgandi.com.net.Handler;

public class SkillHandler {
	
	public static int available = 0;
	
	private static ArrayList<Skill> skills = new ArrayList<Skill>();
	
	public static void init() {
		skills.add(new EnergySkill());
		skills.add(new HealthSkill());
		skills.add(new BowSkill());
		skills.add(new SwordSkill());
	}
	
	public static void upskill(String s, Handler handler) {
		for(int i = 0; i < skills.size(); i++) {
			if(s.toLowerCase().contains(skills.get(i).getName().toLowerCase()) && skills.get(i).getPointDrain() <= available) {
				skills.get(i).updateStats(handler);
				available -= skills.get(i).getPointDrain();
				break;
			}
		}
	}

}
