package org.example.api;

import org.example.api.DTO.AwardRequestDTO;
import org.example.api.DTO.AwardResponseDTO;
import org.example.api.DTO.LotteryRequestDTO;
import org.example.api.DTO.LotteryResponseDTO;
import org.example.types.common.Response;

import java.util.List;

public interface IControllerService {

    Response<Boolean> initializeStrategy(Long strategyId);

    /**
     * Queries the lottery prizes corresponding to the strategy
     * and returns them to the frontend for rendering the list of prizes
     */
    Response<List<AwardResponseDTO>> queryAwardList(AwardRequestDTO awardRequest);

    Response<LotteryResponseDTO> lottery(LotteryRequestDTO lotteryRequestDTO);

}
