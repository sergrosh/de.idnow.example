package service.identification;

import api.Identification;
import org.apache.commons.lang3.StringUtils;
import repository.CompanyRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Identification validator
 *
 * @author Sergii R.
 * @since 17/02/19
 */
public class IdentificatorValidator {

    @Inject
    private CompanyRepository companyRepository;

    /**
     * Check if valid identification model
     *
     * @param identification api model
     * @return list of errors
     */
    public List<String> validate(Identification identification) {
        List<String> errors = new ArrayList<>();

        if (identification.getId() == null) {
            errors.add("Identification id is required!");
        } else if (identification.getId() <= 0) {
            errors.add("Identification id should be greater than zero!");
        }
        if (StringUtils.isEmpty(identification.getName())) {
            errors.add("Company name is required!");
        }
        if (identification.getWaitingTime() == null) {
            errors.add("Waiting time is required!");
        } else if (identification.getWaitingTime() <= 0) {
            errors.add("Identification waiting time should be greater than zero!");
        }
        if (identification.getCompanyId() == null) {
            errors.add("Company id is required!");
        } else {
            if (identification.getCompanyId() <= 0) {
                errors.add("Company id should be greater than zero!");
            } else if (companyRepository.getById(identification.getCompanyId()) == null) {
                errors.add("Company with this id doesn't exist");
            }
        }
        return errors;
    }
}
