/**
 * 实现IService中Lottery的请求部分
 */
package org.example.trigger.http;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.api.DTO.AwardRequestDTO;
import org.example.api.DTO.AwardResponseDTO;
import org.example.api.DTO.LotteryRequestDTO;
import org.example.api.DTO.LotteryResponseDTO;
import org.example.api.IControllerService;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.service.IAwardService;
import org.example.domain.strategy.service.ILotteryService;
import org.example.domain.strategy.service.initializer.IStrategyInitializer;
import org.example.types.common.Response;
import org.example.types.enums.ResponseCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/")
public class LotteryController implements IControllerService {

    @Resource
    private IStrategyInitializer iStrategyInitializer;

    @Resource
    private ILotteryService iLotteryService;

    @Resource
    private IAwardService iAwardService;

    @GetMapping(value = "initialize_strategy")
    @Override
    public Response<Boolean> initializeStrategy(Long strategyId) {
        try {
            log.info("抽奖策略装配开始 strategyId：{}", strategyId);
            Boolean initializeStatus = iStrategyInitializer.initializeStrategy(strategyId);
            Response<Boolean> response = Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(initializeStatus)
                    .build();
            log.info("抽奖策略装配完成 strategyId：{} response: {}", strategyId, JSON.toJSONString(response));
            return response;
        } catch (Exception e) {
            log.error("抽奖策略装配失败 strategyId：{}", strategyId, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @PostMapping(value = "query_award_list")
    @Override
    public Response<List<AwardResponseDTO>> queryAwardList(@RequestBody AwardRequestDTO awardRequest) {
        try {
            log.info("查询抽奖奖品列表配开始 strategyId：{}", awardRequest.getStrategyId());
            // 查询奖品配置
            List<StrategyAwardEntity> strategyAwardEntities = iAwardService.getAwardList(awardRequest.getStrategyId());
            List<AwardResponseDTO> raffleAwardListResponseDTOS = new ArrayList<>(strategyAwardEntities.size());
            for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
                raffleAwardListResponseDTOS.add(AwardRequestDTO.builder()
                        .awardId(strategyAward.getAwardId())
                        .awardTitle(strategyAward.getAwardTitle())
                        .awardSubtitle(strategyAward.getAwardSubtitle())
                        .sort(strategyAward.getSort())
                        .build());
            }
            Response<List<RaffleAwardListResponseDTO>> response = Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(raffleAwardListResponseDTOS)
                    .build();
            log.info("查询抽奖奖品列表配置完成 strategyId：{} response: {}", requestDTO.getStrategyId(), JSON.toJSONString(response));
            // 返回结果
            return response;
        } catch (Exception e) {
            log.error("查询抽奖奖品列表配置失败 strategyId：{}", requestDTO.getStrategyId(), e);
            return Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @PostMapping(value = "lottery")
    @Override
    public Response<LotteryResponseDTO> lottery(@RequestBody LotteryRequestDTO lotteryRequestDTO) {
        return null;
    }
}