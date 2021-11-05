package com.amazonaws.samples.loadtester.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/load-gen")
public class LoadGeneratorController {

    @GetMapping("/loop")
    public Map<String, Object> loopN(
            @RequestParam(name = "count", defaultValue = "50", required = false) int count,
            @RequestParam(name = "range", defaultValue = "1000", required = false) int range,
            @RequestParam(name = "enableOOM", defaultValue = "false", required = false) boolean enableOOM
    ) {

        // 반복 횟수 조정
        if (count < 1) count = 50;
        // 피보나치 수 범위 조정
        if (range < 100 || range > 100000) range = 1000;

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("_count", count);
        jsonMap.put("_range", range);

        Random random = new Random();
        for(int i = 0; i < count; i++) {
            int initNum = random.nextInt(10);
            long[] fibonacciArr = new long[range];
            fibonacciArr[0] = 1;
            fibonacciArr[1] = initNum;

            for(int j = 2; j < fibonacciArr.length; j++) {
                fibonacciArr[j] = fibonacciArr[j-1] + fibonacciArr[j-2];

                if(enableOOM) // For Heavy Memory Usage
                    for(int k= 0; k < j; k++)
                        new String(String.valueOf(fibonacciArr[j]));
            }

            Runtime rt = Runtime.getRuntime();
            long totalMem = rt.totalMemory();
            long maxMem = rt.maxMemory();
            long freeMem = rt.freeMemory();
            double megs = 1048576.0;
            Map<String, Object> memoryMap = new HashMap();
            memoryMap.put("total", totalMem + " (" + (long) (totalMem/megs) + " MiB)");
            memoryMap.put("max",   maxMem + " (" + (long) (maxMem/megs) + " MiB)");
            memoryMap.put("free",  freeMem + " (" + (long) (freeMem/megs) + " MiB)");
            memoryMap.put("used",  freeMem + " (" + ( (long) (totalMem/megs) - (long) (freeMem/megs) ) + " MiB)");

            Map<String, Object> infoMap = new HashMap();
            infoMap.put("initNum", initNum);
            infoMap.put("end", fibonacciArr[fibonacciArr.length-1]);
            infoMap.put("memory", memoryMap);
            jsonMap.put("loop-" + i, infoMap);
        }

        return jsonMap;
    }

}
