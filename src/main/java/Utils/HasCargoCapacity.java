package Utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class HasCargoCapacity extends TypeSafeMatcher<Integer> {

	@Override
	public void describeTo(Description description) {
		description.appendText("cargo capacity : ");
		
	}

	@Override
	protected boolean matchesSafely(Integer item) {
		return item>25000000;
		
	}
	 public static Matcher<Integer>  iscargocapacity (){
		 return new HasCargoCapacity();
	 }
}
