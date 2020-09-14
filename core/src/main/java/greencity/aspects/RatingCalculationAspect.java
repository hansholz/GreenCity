package greencity.aspects;

import greencity.annotations.RatingCalculation;
import greencity.annotations.RatingCalculationEnum;
import greencity.entity.User;
import greencity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * This aspect is used for User rating calculating.
 *
 * @author Taras Dovganyuk
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class RatingCalculationAspect {
    private UserService userService;

    /**
     * This pointcut {@link Pointcut} is used for define annotation to processing.
     *
     * @param ratingCalculation is used for recognize methods to processing.
     */
    @Pointcut("@annotation(ratingCalculation)")
    public void myAnnotationPointcut(RatingCalculation ratingCalculation) {
    }

    /**
     * This method is used for User {@link User} rating calculation.
     *
     * @param ratingCalculation is used for recognize methods to processing.
     * @author Taras Dovganyuk
     */
    @AfterReturning(pointcut = "myAnnotationPointcut(ratingCalculation)",
        argNames = "ratingCalculation")
    private void ratingCalculation(RatingCalculation ratingCalculation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        log.info("ratingCalculation Authentication: " + authentication);
        log.info("ratingCalculation Authentication.Get name: " + authentication.getName());
        log.info("ratingCalculation User name: " + user.getName());
        RatingCalculationEnum rating = ratingCalculation.rating();
        Float rating1 = user.getRating();
        log.info("ratingCalculation user.getRating(): " + rating1);
        float ratingPoints = rating.getRatingPoints();
        log.info("ratingCalculation rating.getRatingPoints(): " + ratingPoints);
        user.setRating(rating1 + ratingPoints);
        userService.save(user);
    }
}
