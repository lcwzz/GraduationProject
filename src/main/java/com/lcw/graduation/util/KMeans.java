package com.lcw.graduation.util;

import com.lcw.graduation.entity.DoctorEvaluation;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class KMeans {
    // 数据集
    private List<DoctorEvaluation> dataset;

    // 中心点
    private List<List<Double>> center = new ArrayList<>();

    // 优秀的集合
    private List<DoctorEvaluation> levelA = new ArrayList<>();

    // 中等的集合
    private List<DoctorEvaluation> levelB = new ArrayList<>();

    // 较差的集合
    private List<DoctorEvaluation> levelC = new ArrayList<>();

    public KMeans(List<DoctorEvaluation> dataset) {
        this.dataset = dataset;
    }

    private double calculateDistance(DoctorEvaluation point, List<Double> centerPoint) {
        int x = point.getX();
        int y = point.getY();
        int z = point.getZ();

        return Math.sqrt(
                (x - centerPoint.get(0)) * (x - centerPoint.get(0)) +
                (y - centerPoint.get(1)) * (y - centerPoint.get(1)) +
                (z - centerPoint.get(2)) * (z - centerPoint.get(2))
        );
    }

    // 归类
    private void sort() {
        for (DoctorEvaluation doctorEvaluation : dataset) {
            double distanceA = calculateDistance(doctorEvaluation, center.get(0));
            double distanceB = calculateDistance(doctorEvaluation, center.get(1));
            double distanceC = calculateDistance(doctorEvaluation, center.get(2));

            double min = Math.min(Math.min(distanceA, distanceB), distanceC);
            if (distanceA == min) {
                doctorEvaluation.setLevel("优秀");
                levelA.add(doctorEvaluation);
            } else if (distanceB == min) {
                doctorEvaluation.setLevel("中等");
                levelB.add(doctorEvaluation);
            } else if (distanceC == min) {
                doctorEvaluation.setLevel("较差");
                levelC.add(doctorEvaluation);
            }
        }
        print();
    }

    // 计算分类的中心点
    private List<Double> calAverage(List<DoctorEvaluation> levelSet) {
        ArrayList<Double> point = new ArrayList<>();
        int xSum = 0, ySum = 0, zSum = 0;
        for (DoctorEvaluation doctorEvaluation : levelSet) {
            xSum += doctorEvaluation.getX();
            ySum += doctorEvaluation.getY();
            zSum += doctorEvaluation.getZ();
        }
        point.add((double) xSum / levelSet.size());
        point.add((double) ySum / levelSet.size());
        point.add((double) zSum / levelSet.size());

        return point;
    }

    private boolean move() {
        boolean flagA = false, flagB = false, flagC = false;
        if (levelA.size() != 0) {
            List<Double> newPoint = calAverage(levelA);
            if (center.get(0).equals(newPoint)) {
                flagA = true;
            } else {
                center.set(0, newPoint);
            }
        }
        if (levelB.size() != 0) {
            List<Double> newPoint = calAverage(levelB);
            if (center.get(1).equals(newPoint)) {
                flagB = true;
            } else {
                center.set(1, newPoint);
            }
        }
        if (levelC.size() != 0) {
            List<Double> newPoint = calAverage(levelC);
            if (center.get(2).equals(newPoint)) {
                flagC = true;
            } else {
                center.set(2, newPoint);
            }
        }
        log.info("中心点：" + center.toString());
        return flagA && flagB && flagC;
    }

    private void clearSet() {
        levelA.clear();
        levelB.clear();
        levelC.clear();
    }

    private void initCenter() {
        center.add(Arrays.asList(10.0, 0.0, 10.0));
        center.add(Arrays.asList(0.0, 0.0, 0.0));
        center.add(Arrays.asList(0.0, -10.0, -10.0));
    }

    public void cluster() {
        // 初始化中心点
        initCenter();

        // 进行聚类
        do {
            // 清空分类集合
            clearSet();
            // 对每个点进行分类
            sort();
            // 更新中心点
        } while (!move());
    }

    public void print() {
        log.info("A" + levelA.toString());
        log.info("B" + levelB.toString());
        log.info("C" + levelC.toString());
    }

    public List<DoctorEvaluation> getDataset() {
        return dataset;
    }

//    public static void main(String[] args) {
//        List<DoctorEvaluation> list = new ArrayList<>();
//        list.add(new DoctorEvaluation(0, "", "", 10, 0, 5));
//        list.add(new DoctorEvaluation(0, "", "", 0, -6, 5));
//        list.add(new DoctorEvaluation(0, "", "", 0, 0, -5));
//        list.add(new DoctorEvaluation(0, "", "", 0, -1, -5));
//        list.add(new DoctorEvaluation(0, "", "",1, -1, -6));
//        KMeans kMeans = new KMeans(list);
//        kMeans.cluster();
//        log.info(kMeans.getDataset().toString());
//    }
}
