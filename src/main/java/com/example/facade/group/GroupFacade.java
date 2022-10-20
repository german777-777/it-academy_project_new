package com.example.facade.group;

import java.util.List;

public interface GroupFacade<RequestCreateDto, RequestUpdateDto, ResponseDto> {
    void saveGroup(RequestCreateDto groupRequestCreateDto);

    ResponseDto getGroupById(Long id);

    List<ResponseDto> getAllGroups();

    void updateGroup(RequestUpdateDto groupRequestUpdateDto);

    void delete(Long id);
}
