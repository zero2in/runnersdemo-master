package hu.gde.runnersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    @Autowired
    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public double getAverageLaptime(Long runnerId) {
        RunnerEntity runner = runnerRepository.findById(runnerId).orElse(null);
        if (runner != null) {
            List<LapTimeEntity> laptimes = runner.getLaptimes();
            int totalTime = 0;
            for (LapTimeEntity laptime : laptimes) {
                totalTime += laptime.getTimeSeconds();
            }
            return (double) totalTime / laptimes.size();
        } else {
            // handle error when runner is not found
            return -1.0;
        }
    }
    public double getAverageRunnerAge(){
        List<RunnerEntity> runner= runnerRepository.findAll();
        if (runner != null){
            int totalRunnerAge = 0;
            for(RunnerEntity r : runner) {
                totalRunnerAge = totalRunnerAge + r.getRunnerAge();
            }
            int counterRunnerAge = runner.size();
            double averageRunnerAge = (double) totalRunnerAge / (double) counterRunnerAge;
            return averageRunnerAge;
        } else {
            return -1.00;
        }
    }
}
