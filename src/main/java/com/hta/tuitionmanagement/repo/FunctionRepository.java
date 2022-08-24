package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.dto.response.AuthorizationDTO;
import com.hta.tuitionmanagement.model.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>, FunctionRepositoryCustom {

    Optional<Function> findById(Long id);

    @Query(value="DELETE FROM function as func WHERE 1=1 AND func.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);

    @Query(value = "WITH temp\n" +
            "        as (\n" +
            "                Select *\n" +
            "                From [function]\n" +
            "                Where parent_code is null\n" +
            "                Union All\n" +
            "                Select b.*\n" +
            "                From temp as a, [function] as b\n" +
            "                Where a.menu_code = b.parent_code\n" +
            "        )\n" +
            "        Select *\n" +
            "        From temp\n" +
            "\t\torder by temp.menu_code",nativeQuery = true)
    List<Function> getAuthorizationList();
}
