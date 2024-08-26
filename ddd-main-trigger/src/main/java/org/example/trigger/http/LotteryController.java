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
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.service.IAwardService;
import org.example.domain.strategy.service.ILotteryService;
import org.example.domain.strategy.service.initializer.IStrategyInitializer;
import org.example.types.common.Response;
import org.example.types.enums.ResponseCode;
import org.example.types.exception.AppException;
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
            List<AwardResponseDTO> awardResponseDTOS = new ArrayList<>(strategyAwardEntities.size());
            for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
                awardResponseDTOS.add(AwardResponseDTO.builder()
                        .awardId(strategyAward.getAwardId())
                        .awardTitle(strategyAward.getAwardTitle())
                        .awardSubtitle(strategyAward.getAwardSubtitle())
                        .sort(strategyAward.getSortOrder())
                        .build());
            }
            Response<List<AwardResponseDTO>> response = Response.<List<AwardResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(awardResponseDTOS)
                    .build();
            log.info("查询抽奖奖品列表配置完成 strategyId：{} response: {}", awardRequest.getStrategyId(), JSON.toJSONString(response));
            // 返回结果
            return response;
        } catch (Exception e) {
            log.error("查询抽奖奖品列表配置失败 strategyId：{}", awardRequest.getStrategyId(), e);
            return Response.<List<AwardResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @PostMapping(value = "lottery")
    @Override
    public Response<LotteryResponseDTO> lottery(@RequestBody LotteryRequestDTO lotteryRequestDTO) {
        try {
            log.info("随机抽奖开始 strategyId: {}", lotteryRequestDTO.getStrategyId());
            // 调用抽奖接口
            LotteryReqEntity lotteryReq = new LotteryReqEntity();
            lotteryReq.setStrategyId(lotteryRequestDTO.getStrategyId());
            lotteryReq.setUserId(lotteryRequestDTO.getUserId());
            LotteryResEntity lotteryRes = iLotteryService.performLottery(lotteryReq);
            // 封装返回结果
            Response<LotteryResponseDTO> response = Response.<LotteryResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(LotteryResponseDTO.builder()
                            .awardId(lotteryRes.getAwardId())
                            .awardIndex(lotteryRes.getSortOrder())
                            .build())
                    .build();
            log.info("随机抽奖完成 strategyId: {} response: {}", lotteryRequestDTO.getStrategyId(), JSON.toJSONString(response));
            return response;
        } catch (AppException e) {
            log.error("随机抽奖失败 strategyId：{} {}", lotteryRequestDTO.getStrategyId(), e.getInfo());
            return Response.<LotteryResponseDTO>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("随机抽奖失败 strategyId：{}", lotteryRequestDTO.getStrategyId(), e);
            return Response.<LotteryResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}