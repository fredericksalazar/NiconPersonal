package org.Nicon.Personal.LibCore.Sbin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NicondateEvaluator
{
  private Date Today;
  private Date evaluate;
  private Calendar CalendarToday;
  private Calendar CalendarEvaluate;
  private SimpleDateFormat format;
  private int edad;
  private int restToBirthday;
  private int factor;
  private String Message;

  public String CalculateBirthDay(String Fecha) {
    try
    {
      if (Fecha == null) {
        Message = "No se encontró información de fechas para el contacto actual.";
      }
      else
      {
        this.evaluate = new SimpleDateFormat("dd/MM/yyyy").parse(Fecha);
        this.CalendarToday = new GregorianCalendar();
        this.CalendarEvaluate = new GregorianCalendar();
        this.Today = new Date();
        this.CalendarToday.setTime(this.Today);
        this.CalendarEvaluate.setTime(this.evaluate);

        this.edad = (this.CalendarToday.get(1) - this.CalendarEvaluate.get(1));

        if (this.CalendarToday.get(2) < this.CalendarEvaluate.get(2)) {
          this.edad -= 1;
        }
        if (this.CalendarToday.get(2) > this.CalendarEvaluate.get(2)) {
          this.edad += 1;
        }
        if ((this.CalendarToday.get(2) == this.CalendarEvaluate.get(2)) && (this.CalendarToday.get(5) < this.CalendarEvaluate.get(5))) {
          this.edad -= 1;
        }
        if ((this.CalendarToday.get(2) != this.CalendarEvaluate.get(2)) || (this.CalendarToday.get(5) <= this.CalendarEvaluate.get(5)));
      }
      this.Message = ("Tiene " + this.edad + " años de edad, Su próximo cumpleaños será en " + ToStringMonthBirthDay() + " Mes(es).");
    }
    catch (Exception e) {
      System.out.println("Ocurrio un error en NicondateEvaluator.CalculateBirthDay() detail:\n " + e);
    }
    return this.Message;
  }

  public int ToStringMonthBirthDay() {
    int Time = this.CalendarEvaluate.get(2);
    return Time;
  }
}