package de.pentabyte.googlemaps;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Skips tests, if specified system property is not set.
 */
public class SystemPropertyPreCondition implements TestRule {
	final String propertyName;

	public SystemPropertyPreCondition(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				if (System.getProperty(propertyName) != null)
					base.evaluate();
				else
					throw new AssumptionViolatedException(
							"System property [" + propertyName + "] not set. Skipping test!");
			}

		};
	}
}