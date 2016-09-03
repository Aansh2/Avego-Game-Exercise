package com.avego.game;

import java.util.Random;

/**
 * Copyright (c) 2016  Ghodratollah Naderi Darehkat
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Project:avego
 * Author: Ghodratollah Naderi
 * E-Mail: Naderi.ghodrat@gmail.com
 * Date  : 5/14/13
 * Time  : 5:09 PM
 * ----------------------------------------------------------------------------
 */
public class Player implements IPlayable, Runnable {
    private int id;
    private int numberOfYellowCard;
    private boolean haveRedCard = false;
    private boolean isEjected = false;
    private int xCoordinate;
    private int yCoordinate;
    private Referee referee;
    private int privateZone;
    private boolean waitingMode = false;
    private boolean gameFinished = false;

    public Player() {
        this.id = 0;
        this.privateZone = 2;
        this.referee = null;
        this.numberOfYellowCard = 0;
    }

    public Player(int id, int privateZone, Referee referee) {
        super();
        this.id = id;
        this.privateZone = privateZone;
        this.referee = referee;

    }

    public int getId() {
        return id;
    }

    public int getNumberOfYellowCard() {
        return numberOfYellowCard;
    }

    public void takeYellowCard() {
        this.numberOfYellowCard++;
    }

    public void setNumberOfYellowCard(int numberOfYellowCard) {
        this.numberOfYellowCard = numberOfYellowCard;
    }

    public boolean isHaveRedCard() {
        return haveRedCard;
    }

    public void setHaveRedCard(boolean haveRedCard) {
        this.haveRedCard = haveRedCard;
    }

    public boolean isEjected() {
        return isEjected;
    }

    public void setEjected(boolean ejected) {
        isEjected = ejected;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getPrivateZone() {
        return privateZone;
    }


    public void move() {
        Random r = new Random();
        if (r.nextBoolean()) {
            if (!r.nextBoolean())
                this.xCoordinate++;
            else
                this.yCoordinate++;
        } else {
            if (!r.nextBoolean())
                this.xCoordinate--;
            else
                this.yCoordinate--;
        }
        referee.checkPlayerMove(this);
    }

    public void waitOutSide() {
        try {
            waitingMode = true;
            System.out.println("Player[" + id + "] waiting outside of the field!");
            Thread.sleep(REENTER_TIME_INTERVAL);
            System.out.println("Player[" + id + "] reenter to the field!");
            waitingMode = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (!isEjected && !gameFinished) {
            try {
                Thread.sleep(MOVE_TIME_INTERVAL);
                move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isWaitingMode() {
        return waitingMode;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }


}
