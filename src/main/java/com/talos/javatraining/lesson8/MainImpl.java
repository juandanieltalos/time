package com.talos.javatraining.lesson8;



import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;


public class MainImpl implements Main
{

	@Override
	public Instant getInstant(String dateTime)
	{
		ZonedDateTime result = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		LocalDate local = LocalDate.parse(dateTime);
		//		Instant start = DateTimeFormatter.ISO_LOCAL_DATE_TIME.form
		//		start.plusSeconds(10);
		//		start.minusSeconds(60*10);

		Instant instant = Instant.parse(dateTime);

		instant.plusSeconds(1);
		instant.minusSeconds(60 * 10);
		return instant;
	}

	@Override
	public Duration getDuration(Instant a, Instant b)
	{
		Duration duration = Duration.between(a, b);
		Duration plusD = duration.plusDays(1);
		Duration minusH = plusD.minusHours(4);
		return minusH;
	}

	@Override
	public String getHumanReadableDate(LocalDateTime localDateTime)
	{
		LocalDateTime plusH = localDateTime.plusHours(3);
		LocalDateTime july = plusH.withMonth(7);
		LocalDateTime nextYear = july.plusYears(1);
		String formatted = DateTimeFormatter.ISO_LOCAL_DATE.format(nextYear);

		return formatted;
	}

	@Override
	public LocalDateTime getLocalDateTime(String dateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ssmmHHddMMyyyy");
		LocalDateTime local = LocalDateTime.parse(dateTime, formatter);
		LocalDateTime plus3m = local.plusMonths(1);
		int seconds = plus3m.getSecond();
		if (seconds > 29)
		{
			LocalDateTime plusMi = plus3m.plusMinutes(1);
			LocalDateTime secx2 = plusMi.withSecond(plus3m.getSecond() * 2 - 60);
			return secx2;
		}
		else
		{
			LocalDateTime secx2 = plus3m.plusSeconds(plus3m.getSecond());
			return secx2;
		}
	}

	@Override
	public Period calculateNewPeriod(Period period)
	{
		Period plus5m = period.plusMonths(5);
		Period plus6d = plus5m.plusDays(6);
		Period min2w = plus6d.minus(Period.ofDays(14));
		return min2w;
	}

	@Override
	public LocalDate toLocalDate(Year year, MonthDay monthDay)
	{
		LocalDate local = LocalDate.of(year.getValue(), monthDay.getMonthValue(), monthDay.getDayOfMonth());
		LocalDate plus3 = local.plusYears(3);
		LocalDate dayM5 = null;
		for (int i = monthDay.getDayOfMonth(); i >= 0; i--)
		{
			if (i % 5 == 0)
			{
				dayM5 = (i != 0) ? plus3.withDayOfMonth(i) : plus3.withDayOfMonth(1);
				break;
			}
		}

		return dayM5;
	}

	@Override
	public LocalDateTime toLocalDateTime(YearMonth yearMonth, int dayOfMonth, LocalTime time)
	{
		LocalDate localDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), dayOfMonth);
		LocalDateTime local = LocalDateTime.of(localDate, time);
		LocalDateTime secZero = local.withSecond(0);
		LocalDateTime minus37 = secZero.minusMinutes(37);
		LocalDateTime plus3d = minus37.plusDays(3);
		return plus3d;
	}

	@Override
	public TemporalAdjuster createTemporalAdjusterNextMonday()
	{
		TemporalAdjuster NEXT_MONDAY = w -> {
			LocalDate local = (LocalDate) w;
			LocalDate result = local.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			return result;
		};
		return NEXT_MONDAY;
	}

	@Override
	public TemporalAdjuster createTemporalAdjusterNextFebruaryFirst()
	{
		TemporalAdjuster February = w -> {
			LocalDate local = (LocalDate) w;
			LocalDate feb = (local.getMonthValue() < 2) ?  local.withMonth(2) : local.plusYears(1).withMonth(2);
			LocalDate result = feb.with(TemporalAdjusters.firstDayOfMonth());
			return result;
		};
		return February;
	}

	@Override
	public String adjustDateTime(String localDateTime, TemporalAdjuster adjuster)
	{

		LocalDateTime local = LocalDateTime.parse(localDateTime);
		LocalDateTime adjLocal = local.with(adjuster);

		String formatted = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(adjLocal);

		return formatted;
	}

	@Override
	public String processZonedDateTime(String zonedDateTime)
	{
		ZonedDateTime zone = ZonedDateTime.parse(zonedDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		ZonedDateTime plus1h = zone.plusHours(1);
		ZonedDateTime utc = plus1h.withZoneSameInstant(ZoneId.of(ZoneOffset.UTC.getId()));
		String formatted = null;
		for (int i = utc.getMinute(); i >= 0; i--)
		{
			if (i % 15 == 0)
			{
				ZonedDateTime minM15 = utc.withMinute(i);
				formatted = DateTimeFormatter.RFC_1123_DATE_TIME.format(minM15);
				break;
			}
		}

		return formatted;
	}
}
