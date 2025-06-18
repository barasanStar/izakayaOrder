package barasanStar.izakayaOrder.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.enums.GroupTokenStatus;
import barasanStar.izakayaOrder.model.entity.VisitGroup;
import barasanStar.izakayaOrder.repository.VisitGroupRepository;
import barasanStar.izakayaOrder.util.DebugLogger;

@Service
public class VisitGroupService {
	@Autowired
	private VisitGroupRepository visitGroupRepository;

	public void setInactive(Long groupId) {
		VisitGroup group = visitGroupRepository.findById(groupId)
				.orElseThrow(() -> new IllegalArgumentException("グループが見つかりません"));
		group.setStatus(GroupTokenStatus.INACTIVE);
		group.setExpiredAt(LocalDateTime.now());
		visitGroupRepository.save(group);
	}

	public String registerGroup(int tableNumber, int numberOfPeople) {
		String groupToken = UUID.randomUUID().toString();
		DebugLogger.log(VisitGroupService.class, "発行したgroupToken=" + groupToken);
		VisitGroup group = new VisitGroup();
		group.setGroupToken(groupToken);
		group.setTableNumber(tableNumber);
		group.setNumberOfPeople(numberOfPeople);
		group.setStatus(GroupTokenStatus.ACTIVE);
		visitGroupRepository.save(group);
		return groupToken;
	}
}
