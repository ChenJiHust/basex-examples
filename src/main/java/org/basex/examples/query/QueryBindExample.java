package org.basex.examples.query;

import java.io.IOException;
import java.io.OutputStream;
import org.basex.core.Context;
import org.basex.data.Result;
import org.basex.data.XMLSerializer;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.item.Item;
import org.basex.query.item.QNm;
import org.basex.query.item.Str;
import org.basex.query.util.Var;
import org.basex.util.Token;

/**
 * This example demonstrates how items can be bound to variables with
 * the BaseX XQuery processor.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-10, ISC License
 * @author BaseX Team
 */
public final class QueryBindExample {
  /** Database context. */
  static final Context CONTEXT = new Context();
  /** Output stream. */
  static final OutputStream OUT = System.out;

  /** Default constructor. */
  protected QueryBindExample() { }

  /**
   * Runs the example code.
   * @param args (ignored) command-line arguments
   * @throws IOException if an error occurs while serializing the results
   * @throws QueryException if an error occurs while evaluating the query
   */
  public static void main(final String[] args)
      throws IOException, QueryException {

    System.out.println("=== QueryBindExample ===");

    // ------------------------------------------------------------------------
    // Evaluate the specified XQuery
    final String query = "declare variable $var external; $var";

    // ------------------------------------------------------------------------
    // Create a query processor
    final QueryProcessor processor = new QueryProcessor(query, CONTEXT);

    // ------------------------------------------------------------------------
    // Create the item to be bound
    final Item string = Str.get("Hello World!\n");

    // ------------------------------------------------------------------------
    // Create a variable
    final Var var = new Var(new QNm(Token.token("var")), true);

    // ------------------------------------------------------------------------
    // Bind the item to the variable
    var.bind(string, processor.ctx);

    // ------------------------------------------------------------------------
    // Add the variable to the global context
    processor.ctx.vars.addGlobal(var);

    // ------------------------------------------------------------------------
    // Execute the query
    final Result result = processor.query();

    // ------------------------------------------------------------------------
    // Serialize all results to OUT, using the specified serializer
    result.serialize(new XMLSerializer(OUT));

    // ------------------------------------------------------------------------
    // Close the query processor
    processor.close();
  }
}