package com.danny.demo.game;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/8.
 */
public class A00001000_包剪锤 {

    public static void main(String[] args) {
        // 进入游戏
    }

    public void startGame() {
        // 玩家出
        Item playerResult = playerAttack();

        // 电脑出
        Item computerResult = computerAttack();

        // 裁判判定
        Result result = judge(playerResult, computerResult);

        // 输出结果
        printResult(result);
    }

    private Item playerAttack() {
        System.out.println("Input your choice: p[paper] c[scissors] s[stone]");
        Scanner scanner = new Scanner(System.in);
        String itemStr = scanner.nextLine();
        Item item = null;
        switch (itemStr) {
            case "p":
                break;
            case "c":
                break;
            case "s":
                break;
            default:
                break;
        }
    }
}

enum Item {
    PAPER,
    SCISSORS,
    STONE;
}

enum Result {
    PLAYER_WIN,
    PLAYER_LOSE,
    DRAW;
}