package com.ectrip.ticket.venuemarketing.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.SeatordertabId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ticket.venuemarketing.dao.impl.PullSeatDAO;

public class PullSeatService {
	private PullSeatDAO pullSeatDao;

	
	public List<Seatordertab> pullSeat(String orid) throws Exception {
		// TODO 获取订单中场馆票 获取到场馆和区域
		List<Seatordertab> orderseat=new ArrayList<Seatordertab>();
		//需引用
		List<TZorderlist> seatinfo = null; //pullSeatDao.getOrderFilmInfo(orid);
		int trytime = 0;
		for (int i = 0; i < seatinfo.size(); i++) {
			TZorderlist zlist = seatinfo.get(i);
			Set seats = pullSeatDao.pullSeat(zlist.getIvenueid(), zlist.getIvenueareaid());// 场馆区域所有的座位
			Set saledSet = pullSeatDao.getSaledSeat(zlist.getDtstartdate(), zlist.getIvenueid(), zlist.getIvenueareaid(), zlist.getTripid());
			Long[] randomseats;
			try {
				// 若抛出异常，说明座位不足，直接跳出
				randomseats = randomSeat(seats, saledSet, zlist.getZnumb().intValue());
			} catch (Exception e) {
				throw e;
			}
			try {
				for (int y = 0; y < randomseats.length; y++) {
					Seatordertab seat = new Seatordertab();
					seat.setId(new SeatordertabId(new Long(y + 1), orid, zlist.getId().getIscenicid(), zlist.getId().getOrderlistid(), zlist.getId().getZorderlistid()));
					seat.setDtmakedate(Tools.getDayTime());
					seat.setIprogramid(zlist.getIvenueseatsid());
					seat.setItripid(zlist.getTripid());
					seat.setIseatid(randomseats[y]);
					seat.setStartdate(zlist.getDtstartdate());
					seat.setIsvalid(1l);
					seat.setItripprdcontrolid(zlist.getIse());
					orderseat.add(seat);
					pullSeatDao.save(seat);
				}
			} catch (Exception e) {
				// 若未保存成功就再试一次，尝试三次未成功直接抛出异常
				if (trytime > 2) {
					throw e;
				} else {
					trytime += 1;
					i -= 1;
				}
			}
			trytime = 0;//初始化尝试次数值
		}
		return orderseat;
	}

	private Long[] randomSeat(Set seats, Set saled, int num) throws Exception {
		seats.removeAll(saled);
		Long[] seatids = (Long[]) seats.toArray();
		Long[] randomseats = new Long[num];
		int[] randomIndex = exclusiveRandom(0, seatids.length, num);
		for (int i = 0; i < randomIndex.length; i++) {
			randomseats[i] = seatids[randomIndex[i]];
		}
		return randomseats;
	}

	public int[] exclusiveRandom(int from, int to, int time) throws Exception {

		// 判断实参是否符合随机取值要求
		if (from > to) {// 指定区间下界大于上界，置换
			to = from + to;
			from = to - from;
			to = to - from;
		}
		// 可取值范围
		int range = to - from;
		if (range == 0) {// 取值范围只有一个数
			int[] randomValue = new int[1];
			randomValue[0] = to;
			return randomValue;
		}
		// 取值范围不能超出界限
		if (time - (range) > 0 || Math.abs(from) != from || Math.abs(to) != to) {
			throw new Exception("没有更多的座位可以出售，请重新选择");
		}
		// 取值次数
		int[] randomValue = new int[time];
		// 可取值范围
		int[] count = new int[range];
		int number;// 当前随机数

		// （１）使用Random类生成随机数

		Random random = new Random();
		for (int i = 0; i < time; i++) {// 生成[10,40]范围内的31个无重复随机数
			number = random.nextInt(range) + from; // 随机数取值区间[0,高值减低值）

			if (0 == count[number - from]) {
				randomValue[i] = number;
				count[number - from]++;// 记录次数
			} else
				i--;// 随机数重复，本次取值无效
		}

		// 输出随机结果及各随机数出现次数
		System.out.println(Arrays.toString(randomValue));

		for (int i = 0; i < count.length; i++) {
			if (0 == count[i])
				continue;
			System.out.println((from + i) + "出现次数：" + count[i]);
			count[i] = 0;// 清除记数
		}
		System.out.println("*****************");

		int[] randomValue2 = new int[time];
		for (int i = 0; i < time; i++) {
			// 伪随机生成[0，指定区间上界-下界]的整数，它与标记存在的数组count[]的元素下标对应
			number = (int) (Math.random() * range);
			// 检查当前随机数是否存在，若存在则放弃
			if (0 == count[number]) {
				randomValue2[i] = number + from;// 加上指定区间下界得到区间内随机数
				count[number]++;
			} else
				i--;
		}
		// 显示结果
		System.out.println(Arrays.toString(randomValue2));
		for (int i = 0; i < count.length; i++) {
			if (0 == count[i])
				continue;
			System.out.println((from + i) + "出现次数：" + count[i]);
			count[i] = 0;// 清除记数
		}

		return randomValue;
	}

	public PullSeatDAO getPullSeatDao() {
		return pullSeatDao;
	}

	public void setPullSeatDao(PullSeatDAO pullSeatDao) {
		this.pullSeatDao = pullSeatDao;
	}
}
