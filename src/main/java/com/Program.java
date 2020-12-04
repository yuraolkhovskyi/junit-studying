package com;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Incremenator extends Thread {
    //О ключевом слове volatile - чуть ниже
    private volatile boolean mIsIncrement = true;
    private volatile boolean mFinish = false;

    public void changeAction()    //Меняет действие на противоположное
    {
        mIsIncrement = !mIsIncrement;
        System.out.println("is increment value - " + mIsIncrement);
    }

    public void finish() {
        mFinish = true;
    }

    @Override
    public void run() {
        System.out.println("START");
        do {
            if (!mFinish) {
                if (mIsIncrement)
                    Program.mValue++;    //Инкремент
                else
                    Program.mValue--;    //Декремент

                //Вывод текущего значения переменной
                System.out.println(Program.mValue + " ");
            } else {
                return;        //Завершение потока
            }

            try {
                Thread.sleep(1000);
                System.out.println("sleeping 1 sek");//Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
                System.out.println("FINISH");
            }
        }
        while (true);
    }
}

public class Program {
    //Переменая, которой оперирует инкременатор
    public static int mValue = 0;

    static Incremenator mInc;    //Объект побочного потока

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        mInc = new Incremenator();    //Создание потока

        System.out.print("Значение = ");

        mInc.start();    //Запуск потока

        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2000); //Ожидание в течении i*2 сек.
            } catch (InterruptedException e) {
            }
            mInc.changeAction();    //Переключение действия
        }

        mInc.finish();    //Инициация завершения побочного потока


        ExecutorService executorService = Executors.newFixedThreadPool(10);


        executorService.execute(() -> System.out.println("testing execute"));

        executorService.submit(() -> System.out.println("testing submit"));


        Callable<String> callable = () -> "task 1";
        Collection<Callable<String>> callables = List.of(callable);
        executorService.invokeAny(callables);


//        executorService.invokeAll()









    }
}
