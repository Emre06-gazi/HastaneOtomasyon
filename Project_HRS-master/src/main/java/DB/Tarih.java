/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author MTxMCT
 */
public class Tarih {
    final int[] tarihBaslangic = {0,2,2019,3,4};
    /*
        3. indis �ubat�n 29 �ekmesini hesaplamak i�in ve 4. indis g�n bilgisini tutar.
        Dizi 0 indisle ba�lad��� i�in g�n ve ay bilgisi i-1 �eklinde tutulur.
    */
    final String[] gunler = {"Pazartesi", "Sal�", "�ar�amba", "Per�embe", "Cuma", "Cumartesi", "Pazar"};
    final String[] aylar = {"Ocak", "�ubat", "Mart", "Nisan", "May�s", "Haziran", "Temmuz", "A�ustos", "Eyl�l", "Ekim", "Kas�m", "Aral�k"};
    
    public String bugununTarihi(){
       // Bugunki tarihi haftan�n g�n�yle beraber d�nd�r�r.
       // 26 Aral�k 2020 Cumartesi gibi
        
        String[] tutucu = this.cumleOlusturGunIle(this.hesap(bugunkiTarihFromDate().split("-")));
        return tutucu[0] + " " + tutucu[1] + " " + tutucu[2] + " " + tutucu[3];
    }
    
    public String bugunkiTarihFromDate(){
    /*
        Sisteme giri� yap�lan g�n� d�nd�r�r.
        2020-12-26 gibi
    */
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();
    return formatter.format(date);
    }
    
    public String bugunkiTarihFromDateSaatIle(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public int[] sayisalDonustur(String[] date){
        // Verilen String tarihi int tarihe d�n��t�r�r
        int j = 0;
        for (int i = 0; i < 7; i++){
            if (gunler[i].equals(date[3])){
                j = i;
                break;
            }
        }
        int k = 0;
        for (int i = 0; i < 12; i++){
            if (aylar[i].equals(date[1])){
                k = i;
                break;
            }
        }
        return new int[]{Integer.parseInt(date[0]), k, Integer.parseInt(date[2]), j};
    }


    public int[] sayisalDonusturRakamlardan(String[] date){
        // Verilen String tarihi int tarihe d�n��t�r�r.
        return new int[]{Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])};
    }


    public String[] cumleOlusturGunIle(int[] date){
        // Verilen int tarihi String tarihe d�n��t�r�r.
        return new String[]{String.valueOf(date[0]+1), aylar[date[1]], String.valueOf(date[2]), gunler[date[4]]};
    }


    public String[] cumleOlustur(int[] date){
        // Verilen int tarihi String tarihe d�n��t�r�r.
        return new String[]{String.valueOf(date[0] + 1), String.valueOf(date[1]), String.valueOf(date[2])};
    }


    public String[] intToStandartDate(int[] date){
        // Verilen int tarihi ({G�n, Ay, Y�l}) {gg,aa,yyyy} format�nda String tarihi d�nd�r�r.
        String[] tutucu = new String[3];
        tutucu[2] = String.valueOf(date[2]);
        if (date[0] >= 0 && date[0] < 10){
            tutucu[0] = "0" + date[0];
        }
        else {
            tutucu[0] = String.valueOf(date[0]);
        }
        if (date[1] >= 0 && date[1] < 10){
            tutucu[1] = "0" + date[1];
        }
        else {
            tutucu[1] = String.valueOf(date[1]);
        }
        return tutucu;
    }


    public String[] stringToStandartDate(String[] date){
        // Verilen String tarihi ({G�n, Ay �smi, Y�l}) {gg,aa,yyyy} format�nda String tarihi d�nd�r�r.
        int[] s = sayisalDonustur(date);
        s[1] = s[1] + 1;
        return  intToStandartDate(s);
    }


    public boolean birincisiBuyuk(int[] date1, int[] date2){
        // �ki tarihi kar��la�t�r�r birincisi b�y�kse true d�nd�r�r.
        if (date1[2] > date2[2]){
            return true;
        }
        else if (date1[1] > date2[1] && date1[2] == date2[2]){
            return true;
        }
        else return date1[0] > date2[0] && date1[1] == date2[1] && date1[2] == date2[2];
    }


    public int[] hesap(String[] date){
        // Verilen tarihin String[] {"26","12,"2020"} haftan�n hangi g�n�ne denk oldu�unu belirler.
        // int[] {26,12,2020,5) d�nd�r�r gibi
        int[] tutucu = sayisalDonusturRakamlardan(date);
        tutucu[0] = tutucu[0] - 1;
        tutucu[1] = tutucu[1] - 1;
        int gun_fark = 0;
        int yil_fark = tutucu[2]-tarihBaslangic[2];

        for (int i = 0 ; i < yil_fark*12 + tutucu[1]-tarihBaslangic[1] ; i++){
            if ((tarihBaslangic[1] + i)%12 == 1){
                gun_fark += 28;
            }
            else if (((tarihBaslangic[1] + i)%12)%2 == 0 && (tarihBaslangic[1] + i)%12 < 7){
                gun_fark += 31;
            }
            else if (((tarihBaslangic[1] + i)%12)%2 == 1 && (tarihBaslangic[1] + i)%12 < 7){
                gun_fark += 30;
            }
            else if (((tarihBaslangic[1] + i)%12)%2 == 0 && (tarihBaslangic[1] + i)%12 > 6){
                gun_fark += 30;
            }
            else if (((tarihBaslangic[1] + i)%12)%2 == 1 && (tarihBaslangic[1] + i)%12 > 6){
                gun_fark += 31;
            }
        }

        gun_fark += tutucu[0];
        gun_fark += (((gun_fark)/365) + tarihBaslangic[3]) /4;
        int suan_gun = (gun_fark + tarihBaslangic[4])%7;

        if (tutucu[0] == 28 && tutucu[1] == 1){
            suan_gun -= 1;
        }
        return new int[]{tutucu[0], tutucu[1], tutucu[2], (((gun_fark) / 365) + tarihBaslangic[3]) % 4, suan_gun};
    }


    public ArrayList<int[]> mevcutGunler(int[] date){
        /*
            Verilen tarihten itibaren ilk 21 g�n i�indeki g�nlerin haftan�n hangi g�n�ne denk geldi�ini belirler.
            Bu g�nleri tarih bilgisiyle beraber d�nd�r�r.
            21 g�n de�i�tirilebilir.
        */
        ArrayList<int[]> ret = new ArrayList<>();
        int[] tutucu;
        for (int i = 0; i < 21; i++){
            date[0] = date[0] + 1;
            date[4] = (date[4] +1)%7;
            if (date[1] == 1){
                if (date[3] == 3){
                    date[1] = date[1] + (date[0]/29);
                    date[0] = date[0]%29;
                }
                else {
                    date[1] = date[1] + (date[0]/28);
                    date[0] = date[0]%28;
                }
            }
            else if (date[1]%2 == 0 && date[1] < 7){
                date[1] = date[1] + (date[0]/31);
                date[0] = date[0]%30;
            }
            else if (date[1]%2 == 1 && date[1] < 7){
                date[1] = date[1] + (date[0]/30);
                date[0] = date[0]%30;
            }
            else if (date[1]%2 == 0 && date[1] > 6){
                date[1] = date[1] + (date[0]/30);
                date[0] = date[0]%30;
            }
            else if (date[1]%2 == 1 && date[1] > 6){
                date[1] = date[1] + (date[0]/31);
                date[0] = date[0]%31;
                if ((date[1] /12) == 1){
                    date[1] = date[1]%12;
                    date[2] = date[2] + 1;
                    date[3] = (date[3] + 1)%4;
                }
            }
            tutucu = new int[]{date[0], date[1], date[2], date[3], date[4]};
            ret.add(tutucu);
        }
        return ret;
    }


    public ArrayList<String []> haftaIci(String[] date){
        /*
            Verilen tarihlerden sadece haftai�i g�nlere sahip olanlar� geri d�nd�r�r.
        */
        ArrayList<int[]> tutucu = mevcutGunler(hesap(date));
        ArrayList<String []> tutucu2 = new ArrayList<>();
        while (!tutucu.isEmpty()){
            int[] s = tutucu.remove(0);
            if (s[4] < 5){
                tutucu2.add(cumleOlusturGunIle(s));
            }
        }
        return tutucu2;
    }
}