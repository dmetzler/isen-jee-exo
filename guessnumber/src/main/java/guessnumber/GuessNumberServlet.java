package guessnumber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guessnumber")
public class GuessNumberServlet extends HttpServlet {

	public static enum Result {
		TOO_HIGH, TOO_LOW, WON, NO_RESULT;
	}

	private static final String NUMBER_KEY = "number";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		createSessionIfNeeded(req);
		Result result = play(req);
		renderResult(req, resp, result);
	}

	private void renderResult(HttpServletRequest req, HttpServletResponse resp, Result result)
			throws ServletException, IOException {
		req.setAttribute("result", result);
		req.getRequestDispatcher("/result.jsp").forward(req, resp);
	}

	private Result play(HttpServletRequest req) {
		try {
			Integer guess = Integer.parseInt(req.getParameter("guess"));
			Integer number = (Integer) req.getSession().getAttribute("number");
			if (guess.equals(number)) {
				return Result.WON;
			} else if (guess > number) {
				return Result.TOO_HIGH;
			} else {
				return Result.TOO_LOW;
			}
		} catch (NumberFormatException e) {
			return Result.NO_RESULT;
		}

	}

	private void createSessionIfNeeded(HttpServletRequest req) {
		Integer number = (Integer) req.getSession().getAttribute(NUMBER_KEY);
		if (number == null) {
			number = (int) Math.round(Math.random() * 10);
			req.getSession().setAttribute(NUMBER_KEY, number);
		}

	}

}
