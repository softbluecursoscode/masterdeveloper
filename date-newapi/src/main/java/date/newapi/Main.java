/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2019 Softblue
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package date.newapi;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {

	public static void main(String[] args) {
		
		String dataNasc = "17/05/1982 22:00:00";
		
		LocalDateTime ldt = LocalDateTime.parse(dataNasc, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		LocalDateTime now = LocalDateTime.now();
		
		Duration d = Duration.between(ldt, now);
		System.out.println("HORAS: " + d.toHours());
		System.out.println("DIAS: " + d.toDays());
		
		Period p = Period.between(ldt.toLocalDate(), now.toLocalDate());
		System.out.println("MESES: " + p.toTotalMonths());
		
		System.out.println("SEMANAS: " + ChronoUnit.WEEKS.between(ldt, now));
		System.out.println("ANOS: " + ChronoUnit.YEARS.between(ldt, now));
		
		YearMonth ym = YearMonth.of(2030, 10);
		System.out.println(ym);
		
		Month month = Month.of(3);
		System.out.println(month);
		
		LocalDate ld = LocalDate.now();
		ld = ld.plusMonths(1);
		ld = ld.minusWeeks(2).plusDays(2);
		System.out.println(ld);
	}
}
