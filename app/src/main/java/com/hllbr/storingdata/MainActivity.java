package com.hllbr.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText ;
    TextView textView ;
    SharedPreferences sharedPreferences ;
    int storedAge;

/*
Veri Saklamak =

Bir çok farklı yöntemle yapılabilir

Veri tabnı kullanmak bunlardan biri

Örneğin yerel veri tabanlarında bilgiler telefonda saklanıyor bu bilgilere sadece ilgili telefondan ulaşabiliyoruz.

Cloud (Internet Bazlı Tabanlı Veri Tabanlarında) bilgiler internette saklanıyor .İstediğim cihazdan bilgilere erişebiliyoum

Çok Küçük verilerle çalışmamız gerekn durumlarda veri tabanı oluşturmak gereksiz bir durum olarabilir.Kullanıcının adını , kullanıcı yaşını yada cinsiyeti gibi basit küçük bilgileri saklamak için

Bu tip verileri telefonun hafızasında tutyabilmek için bir obje oluşturulmuş sharedpreferenses

Bu yapıyı OnCreate içerisinde tanımlamak doğru olan yöntem app açıldığında ilk yapıalcak işlemler arasında bu yapı ile ilgili işlemlerim olmalı

 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.numberText);

        textView = findViewById(R.id.resultText);


/*
bu oluşturduğum yapı bana küçük bir veri tabaı veriyor telefon kapansada açılsada app baştan yazılsada vb... app silinmediği sürece sakladığım verielere ulaşabilir hale gelicem

Bu yapıdan obje oluşturmak dsiğerlerinden biraz daha farklı

MainActivity içerisinde this anahtar kelimesi ile ulaşabiliyorum

 */
        sharedPreferences =this.getSharedPreferences("com.hllbr.storingdata", Context.MODE_PRIVATE);

        //Yapı Kydetmek için kullanılan yapım

        storedAge = sharedPreferences.getInt("storedAge",0);

        //Kaydedilen veriyi geri almak için kullanılan yapı


        //bu alanda getInt içersinde anahtar kelime aynı olacak şekilde yazılır.İkinci ksımda ise default olduğu durumda alınacak değeri ifade ederim

        if(storedAge == 0 ){
            textView.setText("YOUR AGE ::");
        }else{
            textView.setText("YOUR AGE : "+storedAge);
        }
        /*

        Veri üzerinde güncelleme yapmak = aynı key ile farklı değer kullanmak demek şuan kod yapım güncelleme yapıyor.

        Eğer Kaydedilmiş değeri komple silmek istersem farklı bir işlem yapmam gerekiyor.

        Bunun için deletebut adlı bir onClik metodu yazıyorum ve gerekli işlemleri burada gerçekleştiriyorum

         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(storedAge == 0){
            textView.setText("YOUR AGE :!");
        }else{
            textView.setText("YOUR AGE : "+storedAge);
        }

    }

    public void save(View view){

        if(editText.getText().toString().matches("")){

            textView.setText("PLEASE ENTER AN AGE ");

        }else{

            int age = Integer.parseInt(editText.getText().toString());

            textView.setText("YOUR AGE : "+age);

            //şuan yapı bilgi saklamıyor sadece anlık işlemlerin sonuçlarını göstertiyor pause yada resume yada stop gibi durumlarda bilgi kalıcı durumdad değil.

            sharedPreferences.edit().putInt("storedAge",age).apply();

            //.edit() = düzenlemek için put ile hangi veri tipinden bir koyma(yerleştirme işlemi ) yapacağımı ifade ediyorum .apply() ile uygulmamak için kullanıyoruz

            //bu yapı kurulduğunda verimiz veri tabanında saklanıyor.İstediğim zaman veriye ulaşabiliyorum .App silinmediği sürece bu veri bu uygulamanın içerinde kalıyor

            //Bu kısım onresume kısmındaa yapılabilir.

        }

    }
    public void deletebut(View view){
        //delete buton basıldığında bir kontrol gerçekleşitirerek boş olup olmadığını denetleyip ardından işlemleri yapmak istiyorum

        int storedData = sharedPreferences.getInt("storedAge",0);

        if(storedData == 0 ){

            //eğer storedData boşsa herhangi bir silme işlemine gerek yok

        }else{

            sharedPreferences.edit().remove("storedAge").apply();//verdiğim anahtar kelimeyle kayıtlı bir veri varsa değeri sil anlamına geliyor kurduğum yapı

            //edit() düzenle remove sil .apply ise uygula anlamına gelmektedir.Kurduğum yapıda
            textView.setText("Your Age : ? ");
            
            //Yüzlerce kullanıcının adını kaydetmek yada buna benzer bir kayıtr gerçekleştirmek istersem yüzlerce sharedPreferences oluşturmam tanımlamam gerekeceği için gereksiz ve anlamsız hale geliyor ma bu şekilde basit küçük bilgi(tanecikleri) ile işlemler yapılacaksa en uygun yöntem diye biliriz



        }

    }

}