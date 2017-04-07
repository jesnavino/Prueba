
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Budget;
import domain.Material;
import repositories.MaterialRepository;

@Service
@Transactional
public class MaterialService {

	@Autowired
	private MaterialRepository	materialRepository;

	@Autowired
	private BudgetService		budgetService;


	public MaterialService() {
		super();
	}

	public Material create(final int budgetId) {
		Material material;
		Budget budget;

		material = new Material();

		budget = this.budgetService.findOne(budgetId);
		material.setBudget(budget);

		return material;
	}

	public Material save(final Material material) {
		Assert.notNull(material);

		return this.materialRepository.saveAndFlush(material);

	}

	public Material findOne(final int materialId) {
		Assert.isNull(materialId);

		return this.materialRepository.findOne(materialId);
	}
}
