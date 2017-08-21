package com.example;

/**
 * Created by 337965 on 2017/8/4.
 */

public class InnerClassTest {

    class A{

        public void sit(){
            init();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }


    class B{

    }


    public void init(){

    }


}
